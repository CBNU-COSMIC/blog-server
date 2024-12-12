from pydantic import BaseModel


class UsersReadDTO(BaseModel):
    name: str
    nickname: str
