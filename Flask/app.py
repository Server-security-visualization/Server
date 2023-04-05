import configparser
import tensorflow as tf
from flask import Flask
from tensorflow.python.client import device_lib
from mariadb_init import init_db, packet_table, packet_detection_table, file_table, file_detection_table, block_user_table, auth_table

app = Flask(__name__)

try:
    config = configparser.ConfigParser()
    config.read('/usr/src/app/config.ini')
    dbuser = config['MariaDB']['user']
    password = config['MariaDB']['password']
    database = config['MariaDB']['database']
    port = int(config['MariaDB']['port'])

    db = {
        'user':dbuser,
        'password':password,
        'host':'mariadb',
        'port':port,
        'database':database
    }
except:
    print("MariaDB Connection ERROR!")
    exit(0)

try:
    print(device_lib.list_local_devices())
    malware_model_path = "./model/malware_cnn"
    webattack_model_path = "./model/webattack_detection_rf_model"

    malware_model = tf.keras.models.load_model(malware_model_path)
    webattack_model = tf.keras.models.load_model(webattack_model_path)
except:
    print("Model Load ERROR!")
    exit(0)

@app.route('/')
def hello_world():
    return 'Hello World!'

app.run(host="0.0.0.0", port=5000, threaded=True)