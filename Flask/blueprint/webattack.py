import numpy as np
import tensorflow as tf
from PIL import Image
from flask import Blueprint, request
from tensorflow.keras.layers import Convolution1D, MaxPooling1D, BatchNormalization, Bidirectional, LSTM, Reshape, Dense, Dropout, Activation

blue_webattack = Blueprint("webattack", __name__, url_prefix="/webattack")

def CNN_BiLSTM():
    model = tf.keras.Sequential()
    model.add(Convolution1D(64, kernel_size=32, padding="same",
              activation="relu", input_shape=(76, 1)))
    model.add(MaxPooling1D(pool_size=(5)))
    model.add(BatchNormalization())
    model.add(Bidirectional(LSTM(64, return_sequences=False)))
    model.add(Reshape((128, 1), input_shape=(128, )))

    model.add(MaxPooling1D(pool_size=(5)))
    model.add(BatchNormalization())
    model.add(Bidirectional(LSTM(128, return_sequences=False)))

    model.add(Dropout(0.5))
    model.add(Dense(4))
    model.add(Activation('softmax'))
    model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['accuracy'])
    return model

try:
    thresh = 0.5
    
    epoch = 30
    checkpoint_path = f"./model/packet_model/model_{epoch}.ckpt"

    packet_model = CNN_BiLSTM()
    packet_model.load_weights(checkpoint_path)
    packet_model.summary()
except:
    print("WebAttack Model Load ERROR!")
    exit(0)

@blue_webattack.route('/', methods=["POST"], strict_slashes=False)
def malware():
    if(request.method=="POST"):
        return "Test"