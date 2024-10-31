import bcrypt
from sqlalchemy.orm import Session

from app.models.user_entity import UserEntity


def get_user_by_id(db: Session, id):
    return db.query(UserEntity).filter(UserEntity.id == id).first()

def encrypt_password(user : UserEntity):
    user.password = bcrypt.hashpw(user.password.encode('utf-8'), bcrypt.gensalt())
    return user