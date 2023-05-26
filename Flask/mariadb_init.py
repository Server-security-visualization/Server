from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()

def init_db(app):
    db.init_app(app)
    with app.app_context():
        db.create_all()
    return db

class web_log_table(db.Model):
    idx = db.Column(db.Integer, primary_key=True, autoincrement=True)
    direction = db.Column(db.Integer, nullable=False)
    http_method = db.Column(db.String(10), nullable=False)
    http_query = db.Column(db.String(500), nullable=False)
    http_version = db.Column(db.String(10), nullable=False)
    http_url = db.Column(db.String(500), nullable=False)
    http_status = db.Column(db.Integer, nullable=False)
    pkt_bytes = db.Column(db.Integer, nullable=False)
    rcvd_bytes = db.Column(db.Integer, nullable=False)
    sent_bytes = db.Column(db.Integer, nullable=False)
    referer = db.Column(db.String(500), nullable=False)
    timestamp = db.Column(db.DateTime, nullable=False)

class web_log_detection_table(db.Model):
    idx = db.Column(db.Integer, primary_key=True, autoincrement=True)
    detection = db.Column(db.Double, nullable=False)
    web_log_idx = db.Column(db.Integer, nullable=False)

class file_table(db.Model):
    idx = db.Column(db.Integer, primary_key=True, autoincrement=True)
    ip = db.Column(db.String(15), nullable=False)
    filename = db.Column(db.String(100), nullable=False)
    file = db.Column(db.LargeBinary, nullable=False)
    timestamp = db.Column(db.DateTime, nullable=False)

class file_detection_table(db.Model):
    idx = db.Column(db.Integer, primary_key=True, autoincrement=True) 
    img = db.Column(db.LargeBinary, nullable=False)
    detection = db.Column(db.Double, nullable=False)
    type = db.Column(db.String(20), nullable=False)
    file_idx = db.Column(db.Integer, nullable=False)

class block_user_table(db.Model):
    ip = db.Column(db.String(15), primary_key=True)
    time = db.Column(db.DateTime, nullable=False)
    reason = db.Column(db.String(15), nullable=False)
    
class auth_table(db.Model):
    uuid = db.Column(db.String(36), primary_key=True)