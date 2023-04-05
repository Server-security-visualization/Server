import os
import configparser
import tensorflow as tf
from flask import Flask
from tensorflow.python.client import device_lib
from mariadb_init import init_db
from blueprint import malware, webattack

app = Flask(__name__)
app.config['JSON_AS_ASCII'] = False
app.secret_key = os.urandom(32)

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
    app.config['SQLALCHEMY_DATABASE_URI'] = f"mysql+mysqlconnector://{db['user']}:{db['password']}@{db['host']}:{db['port']}/{db['database']}?charset=utf8mb4&collation=utf8mb4_general_ci"

    db = init_db(app)
except:
    print("MariaDB Connection ERROR!")
    exit(0)

print(device_lib.list_local_devices())
    
app.register_blueprint(malware.blue_malware)
app.register_blueprint(webattack.blue_webattack)

app.run(host="0.0.0.0", port=5000, threaded=True)