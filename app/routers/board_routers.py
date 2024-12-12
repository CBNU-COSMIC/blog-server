from fastapi import APIRouter, Depends
from sqlalchemy.orm import Session

from app.schemas.board.posts_count_dto import PostsCountDTO
from app.services import post_service
from databases import get_db

router = APIRouter(prefix="/api/boards")


@router.get("/{boardId}/posts/count")
def get_posts_count(boardId: str, db: Session = Depends(get_db)) -> PostsCountDTO:
    count = post_service.get_posts_count_by_board_id(board_id=boardId, db=db)
    return PostsCountDTO(count=count)
