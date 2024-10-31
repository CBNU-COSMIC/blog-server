from sqlalchemy.orm import Session

from app.models.user import User
from app.models.user_entity import UserEntity


def get_user_by_id(db: Session, id):
    return db.query(UserEntity).filter(UserEntity.id == id).first()

def create_user(db: Session, user: User):
    user_entity = UserEntity(id= user.id, name= user.name, member_id= user.member_id, password= user.password, role= user.role, avatar= user.avatar, phone_number= user.phone_number, student_number= user.student_number, birth= user.birth, email= user.email)
    return db.add(user_entity)
