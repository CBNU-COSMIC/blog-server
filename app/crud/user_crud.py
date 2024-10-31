from sqlalchemy.orm import Session

from app.models.user_entity import UserEntity


def get_user_by_id(db: Session, id):
    """
    id를 이용하여 id에 해당하는 User를 찾습니다.
    """
    return db.query(UserEntity).filter(UserEntity.id == id).first()