from fastapi import APIRouter, Depends, Request, HTTPException
from sqlalchemy.orm import Session

from app.schemas.user.user_read_dto import UserReadDTO
from app.schemas.user.user_role_update_dto import UserRoleUpdateDto
from app.schemas.user.user_update_dto import UserUpdateDTO
from app.schemas.user.users_read_dto import UsersReadDTO
from app.services import user_service
from databases import get_db

router = APIRouter(prefix='/api/users')


@router.get('/')
def get_users(request: Request, db: Session = Depends(get_db)) -> None:
    user = request.state.user
    if user['role'] != 'president':
        raise HTTPException(
            status_code=403,
            detail="권한이 없습니다."
        )

    users = user_service.get_users(db=db)
    return [
        UsersReadDTO(
            name=user.name,
            nickname=user.nickname
        )
        for user in users
    ]


@router.get('/me')
def get_user(request: Request, db: Session = Depends(get_db)) -> None:
    user = user_service.get_user_by_id(db=db, user_id=request.state.user['user_id'])
    return UserReadDTO(
        name=user.name,
        member_id=user.member_id,
        nickname=user.nickname,
        role=user.role,
        phone_number=user.phone_number,
        student_number=user.student_number,
        birth=user.birth,
        email=user.email
    )


@router.get('/{nickname}')
def get_user_by_nickname(request: Request, nickname: str, db: Session = Depends(get_db)) -> None:
    if request.state.user['role'] != 'president':
        raise HTTPException(
            status_code=403,
            detail="권한이 없습니다."
        )

    user = user_service.get_user_by_nickname(db=db, nickname=nickname)
    return UserReadDTO(
        name=user.name,
        member_id=user.member_id,
        nickname=user.nickname,
        role=user.role,
        phone_number=user.phone_number,
        student_number=user.student_number,
        birth=user.birth,
        email=user.email
    )


@router.put('/')
def update_user(request: Request, user_update_dto: UserUpdateDTO, db: Session = Depends(get_db)) -> None:
    user = request.state.user
    user_service.update_user(db=db, user_id=user['user_id'], nickname=user_update_dto.nickname,
                             email=user_update_dto.email,
                             username=user_update_dto.username, birth=user_update_dto.birth,
                             phone=user_update_dto.phone)


@router.put('/{nickname}/role')
def update_user_role(request: Request, nickname: str, role: UserRoleUpdateDto, db: Session = Depends(get_db)) -> None:
    user = request.state.user
    if user['role'] != 'president':
        raise HTTPException(
            status_code=403,
            detail="권한이 없습니다."
        )

    user_service.update_user_role(db=db, nickname=nickname, role=role.role)
