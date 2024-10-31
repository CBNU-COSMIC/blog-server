from sqlalchemy.orm import Session

from app.models.user_entity import UserEntity


def get_user_by_id(db: Session, id):
    #id로 User 찾는 함수
    return db.query(UserEntity).filter(UserEntity.id == id).first()