from sqlalchemy.orm import Session
from app.models.user_entity import UserEntity
from typing import Optional

def get_user_by_id(db: Session, id):
    return db.query(UserEntity).filter(UserEntity.id == id).first()

# 학번으로 사용자 조회
def get_user_by_student_number(db: Session, student_number: str) -> Optional[UserEntity]:
    return db.query(UserEntity).filter(UserEntity.student_number == student_number).first()

# 학번으로 사용자 아이디 조회
def get_member_id_by_student_number(db: Session, student_number: str) -> Optional[str]:
    user = db.query(UserEntity).filter(UserEntity.student_number == student_number).first()
    return user.member_id if user else None