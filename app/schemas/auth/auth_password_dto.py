from pydantic import BaseModel


class AuthPasswordDTO(BaseModel):
    password: str
