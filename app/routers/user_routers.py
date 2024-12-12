from fastapi import APIRouter, Depends, Request
from sqlalchemy.orm import Session

from app.schemas.user.user_update_dto import UserUpdateDTO
from app.services import user_service
from databases import get_db

router = APIRouter(prefix='/api/users')


@router.put('/')
def update_user(request: Request, user_update_dto: UserUpdateDTO, db: Session = Depends(get_db)) -> None:
    user = request.state.user
    user_service.update_user(db=db, user_id=user['user_id'], nickname=user_update_dto.nickname,
                             email=user_update_dto.email,
                             username=user_update_dto.username, birth=user_update_dto.birth,
                             phone=user_update_dto.phone)
