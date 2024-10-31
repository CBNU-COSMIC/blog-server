from sqlalchemy.orm import Session
from fastapi import HTTPException
from app.crud.user_crud import get_user_by_student_number, get_member_id_by_student_number
from app.models.user import User
from typing import Optional

class UserService:
    def __init__(self, db: Session):
        self.db = db
    
    # 학번으로 회원 중복 여부 검사
    def validate_duplicate_user(self, student_number: str) -> None:
        existing_user = self._find_existing_user(student_number)
        
        if existing_user:
            raise HTTPException(
                status_code=409,  # HTTP 409 Conflict
                detail="이미 가입된 회원입니다."
            )
    
    # 학번으로 회원 아이디 조회
    def get_member_id(self, student_number: str) -> Optional[str]:
        if not student_number:
            raise HTTPException(
                status_code=400,
                detail="학번을 입력해주세요."
            )
            
        member_id = get_member_id_by_student_number(self.db, student_number)
        
        if not member_id:
            return None
            
        return member_id
    
    # 학번으로 기존 회원 조회
    def _find_existing_user(self, student_number: str) -> Optional[User]:
        user_entity = get_user_by_student_number(self.db, student_number)
        
        if user_entity:
            return User(
                id=user_entity.id,
                name=user_entity.name,
                member_id=user_entity.member_id,
                password=user_entity.password,
                role=user_entity.role,
                avatar=user_entity.avatar,
                phone_number=user_entity.phone_number,
                student_number=user_entity.student_number,
                birth=user_entity.birth,
                email=user_entity.email
            )
        return None