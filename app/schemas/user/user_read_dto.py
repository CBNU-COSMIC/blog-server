from datetime import datetime

from pydantic import BaseModel


class UserReadDTO(BaseModel):
    name: str
    member_id: str
    nickname: str
    role: str
    phone_number: str
    student_number: str
    birth: datetime
    email: str
