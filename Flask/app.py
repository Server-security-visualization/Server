from flask import Flask
import tensorflow as tf
from tensorflow.python.client import device_lib

app = Flask(__name__)
print(device_lib.list_local_devices())

@app.route('/')
def hello_world():
    return 'Hello World!'

app.run(host="0.0.0.0", port=5000)