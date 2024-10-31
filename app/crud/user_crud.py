from sqlalchemy.orm import Session

from app.models.user_entity import UserEntity


def get_user_by_member_id(db: Session, member_id):
    return db.query(UserEntity).filter(UserEntity.member_id == member_id).first()