from datetime import datetime

from pydantic import BaseModel


class UserUpdateDTO(BaseModel):
    nickname: str
    email: str
    username: str
    birth: datetime
    phone: str
