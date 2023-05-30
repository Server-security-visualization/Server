import joblib
import datetime
import configparser
import numpy as np
from flask import Blueprint, request
from mariadb_init import web_log_table, web_log_detection_table, db
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.ensemble import RandomForestClassifier

blue_webattack = Blueprint("webattack", __name__, url_prefix="/webattack")

try:
    weblog_model = joblib.load('./model/weblog_model/RandomForest.pkl')
    http_query_vec = joblib.load('./model/weblog_model/http_query_vec.pkl')
    http_url_vec = joblib.load('./model/weblog_model/http_url_vec.pkl')
    referer_vec = joblib.load('./model/weblog_model/referer_vec.pkl')
    
except:
    print("WebAttack Model Load ERROR!")
    exit(0)

def preprocessing_log(direction, http_method, http_query, http_version, http_url, http_status, pkt_bytes, rcvd_bytes, sent_bytes, referer):
    log = np.zeros(2, dtype=np.int64)
    
    # direction
    if(direction == 1):
        log[0] = 1
    else:
        log[1] = 1
        
    # http_method
    method_dict = {
        'GET':0,
        'CONNECT':1,
        'POST':2,
        'GETS':3,
        'PUT':4,
        'DEBUG':5,
        'TRACE':6
    }
    method_len = len(method_dict)
    http_method_arr = np.zeros(method_len, dtype=np.int64)
    http_method_arr[method_dict[http_method]] = 1
    log = np.append(log, http_method_arr)
    
    
    # http_query
    log = np.append(log, http_query_vec.transform([http_query]).toarray()[0])

    # http_version
    version_dict = {
        'HTTP/1.0':0,
        'HTTP/1.1':1
    }
    version_len = len(version_dict)
    http_version_arr = np.zeros(version_len, dtype=np.int64)
    http_version_arr[version_dict[http_version]] = 1
    log = np.append(log, http_version_arr)
    
    # http_url
    log = np.append(log, http_url_vec.transform([http_url]).toarray()[0])
    
    # http_status
    status_dict = {
        '200':0,
        '302':1,
        '304':2,
        '401':3,
        '403':4,
        '404':5,
        '405':6,
        '500':7,
        '501':8
    }
    status_len = len(status_dict)
    http_status_arr = np.zeros(status_len, dtype=np.int64)
    http_status_arr[status_dict[str(http_status)]] = 1
    log = np.append(log, http_status_arr)
    
    # referer
    log = np.append(log, referer_vec.transform([referer]).toarray()[0])
    
    # ETC
    log = np.append(log, np.array([pkt_bytes, rcvd_bytes, sent_bytes], dtype=np.int64))

    return log

@blue_webattack.route('/', methods=["POST"], strict_slashes=False)
def malware():
    if(request.method=="POST"):
        ip = request.form.get("ip", "")
        direction = request.form.get("direction", -1, type=int)
        http_method = request.form.get("http_method", "")
        http_query = request.form.get("http_query", "")
        http_version = request.form.get("http_version", "")
        http_url = request.form.get("http_url", "")
        http_status = request.form.get("http_status", -1, type=int)
        pkt_bytes = request.form.get("pkt_bytes", -1, type=int)
        rcvd_bytes = request.form.get("rcvd_bytes", -1, type=int)
        sent_bytes = request.form.get("sent_bytes", -1, type=int)
        referer = request.form.get("referer", "")
        
        if(ip != "" and direction != -1 and http_method != "" and http_query != "" and http_version != "" and http_url != "" and http_status != -1 and pkt_bytes != -1 and rcvd_bytes != -1 and sent_bytes != -1 and referer != ""):
            new_log = web_log_table(idx=None, direction=direction, http_method=http_method, http_query=http_query, http_version=http_version, http_url=http_url, http_status=http_status, pkt_bytes=pkt_bytes, rcvd_bytes=rcvd_bytes, sent_bytes=sent_bytes, referer=referer, timestamp=datetime.datetime.now())
            
            db.session.add(new_log)
            db.session.commit()
            
            log = preprocessing_log(direction, http_method, http_query, http_version, http_url, http_status, pkt_bytes, rcvd_bytes, sent_bytes, referer)
            prediction = weblog_model.predict([log])[0]
            
            if(prediction == "attack"):
                prediction = 1
            else:
                prediction = 0
            
            new_log_detection = web_log_detection_table(idx=None, detection=prediction, web_log_idx=new_log.idx)
            db.session.add(new_log_detection)
            db.session.commit()
            
            return "success"
        else:
            return "form data error!"
        