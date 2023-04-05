import numpy as np
import tensorflow as tf
from PIL import Image
from flask import Blueprint, request

blue_webattack = Blueprint("webattack", __name__, url_prefix="/webattack")

try:
    webattack_model_path = "./model/webattack_detection_rf_model"

    webattack_model = tf.keras.models.load_model(webattack_model_path)
except:
    print("WebAttack Model Load ERROR!")
    exit(0)

@blue_webattack.route('/', methods=["POST"], strict_slashes=False)
def malware():
    if(request.method=="POST"):
        return "Test"