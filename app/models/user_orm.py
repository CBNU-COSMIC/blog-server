from sqlalchemy import Column, String, Integer, DateTime
from sqlalchemy.ext.declarative import declarative_base
import datetime

Base = declarative_base()

class UserORM(Base):
    __tablename__ = 'Member'

    id = Column(String, primary_key=True, foreign_key=True)
    name = Column(String, nullable=False)
    member_id = Column(String, nullable=False)
    password = Column(String, nullable=False)
    role = Column(String)
    avatar = Column(String)
    phone_number = Column(String)
    student_number = Column(String, nullable=False)
    birth = Column(DateTime)
    email = Column(String)

    def __init__(self, id, name, member_id, password, role, avatar, phone_number, student_number, birth, email):
        self.id = id
        self.name = name
        self.member_id = member_id
        self.password = password
        self.role = role
        self.avatar = avatar
        self.phone_number = phone_number
        self.student_number = student_number
        self.birth = birth
        self.email = email
