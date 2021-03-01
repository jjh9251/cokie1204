package board.model.dao;

import static common.JDBCTemplate.close;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import board.model.vo.Notice;


public class NoticeDao {

	private Properties prop = new Properties();

	/**
	 * build-path의 board-query.properties의 내용을 읽어와 필드 prop에 저장한다.
	 */
	public NoticeDao() {
		try {
			//클래스객체 위치찾기 : 절대경로를 반환한다. 
			String fileName = NoticeDao.class.getResource("/sql/notice-query.properties").getPath();
			prop.load(new FileReader(fileName));

		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public List<Notice> selectNoticeList(Connection conn, int cpage, int numPerPage) {
	
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Notice> list = new ArrayList<>();
		System.out.println("conn시작");
		System.out.println(conn);
		System.out.println("conn끝");
		String query = prop.getProperty("selectNoticeList");
		System.out.println(query);
		try{
			//미완성쿼리문을 가지고 객체생성. 
				pstmt = conn.prepareStatement(query);
			
			//시작 rownum과 마지막 rownum 구하는 공식
			pstmt.setInt(1, (cpage-1)*numPerPage+1);
			pstmt.setInt(2, cpage*numPerPage);
			
			//쿼리문실
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Notice b = new Notice();
				
				b.setNoticeNum(rset.getInt("notice_num"));
				b.setNoticeTitle(rset.getString("notice_title"));
				b.setNoticeWriter(rset.getString("notice_writer"));
				b.setNoticeContent(rset.getString("notice_content"));
				b.setNoticeOriginalImage(rset.getString("notice_original_Image"));
				b.setNoticeRenameImage(rset.getString("notice_rename_Image"));
				b.setNoticeDate(rset.getDate("notice_date"));
				b.setNoticeDelete(rset.getString("notice_delete"));
				list.add(b);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			//런타임예외로 전환해서 다시 던지기
		//	throw new RuntimeException("게시물 조회 오류", e);
		}finally{
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}
	
	

	public int selectNoticeCount(Connection conn) {
		PreparedStatement pstmt = null;
		int totalContents = 0;
		ResultSet rset = null;
	String query = prop.getProperty("selectNoticeCount");
		
	try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			
			///쿼리문실행
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				totalContents = rset.getInt("cnt");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return totalContents;
	}

//	public int insertBoard(Connection conn, Board board) {
//		PreparedStatement pstmt = null;
//		int result = 0;
//		String sql = prop.getProperty("insertBoard");
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, board.getBoardTitle());
//			pstmt.setString(2, board.getBoardWriter());
//			pstmt.setString(3, board.getBoardContent());
//			pstmt.setString(4, board.getBoardOriginalFileName());
//			pstmt.setString(5, board.getBoardRenamedFileName());
//			
//			result = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
//		
//		return result;
//	}
//
//	public Board selectOne(Connection conn, int boardNo) {
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		String query = prop.getProperty("selectOne");
//		//select * from board where board_no = ?
//		Board b = null;
//		
//		try{
//			//미완성쿼리문을 가지고 객체생성. 
//			pstmt = conn.prepareStatement(query);
//			pstmt.setInt(1, boardNo);
//			
//			//쿼리문실행
//			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
//			rset = pstmt.executeQuery();
//			while(rset.next()){
//				b = new Board();
//				//컬럼명은 대소문자 구분이 없다.
//				b.setBoardNo(rset.getInt("board_no"));
//				b.setBoardTitle(rset.getString("board_title"));
//				b.setBoardWriter(rset.getString("board_writer"));
//				b.setBoardContent(rset.getString("board_content"));
//				b.setBoardOriginalFileName(rset.getString("board_original_filename"));
//				b.setBoardRenamedFileName(rset.getString("board_renamed_filename"));
//				b.setBoardDate(rset.getDate("board_date"));
//				b.setBoardReadCount(rset.getInt("board_read_count"));
//			}
//			
//		}catch(Exception e){
//			//런타임예외, 구체적인 의미를 가진 예외객체로 전환해서 다시 던지기
//			throw new BoardException("게시물 조회 오류", e);
//		}finally{
//			close(rset);
//			close(pstmt);
//		}
//		return b;
//	}
//
//	/**
//	 * 게시글 등록 직후, 게시글 번호를 가져온다.
//	 * @param conn
//	 * @return
//	 */
//	public int selectLastBoardNo(Connection conn) {
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		String sql = prop.getProperty("selectLastBoardNo");
//		int boardNo = 0;
//		try {
//			pstmt = conn.prepareStatement(sql);
//			rset = pstmt.executeQuery();
//			if(rset.next())
//				boardNo = rset.getInt(1);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(rset);
//			close(pstmt);
//		}
//		return boardNo;
//	}
//
//	public int updateBoardReadCount(Connection conn, int boardNo) {
//		PreparedStatement pstmt = null;
//		int result = 0;
//		String sql = prop.getProperty("updateBoardReadCount");
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, boardNo);
//			result = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
//		return result;
//	}
//
//	public int deleteBoard(Connection conn, int boardNo) {
//		PreparedStatement pstmt = null;
//		int result = 0;
//		String sql = prop.getProperty("deleteBoard");
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, boardNo);
//			result = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
//		return result;
//	}
//	
//	public int updateBoard(Connection conn, Board b) {
//		int result = 0;
//		PreparedStatement pstmt = null;
//		String query = prop.getProperty("updateBoard"); 
//		
//		try {
//			//미완성쿼리문을 가지고 객체생성.
//			pstmt = conn.prepareStatement(query);
//			//쿼리문미완성
//			pstmt.setString(1, b.getBoardTitle());
//			pstmt.setString(2, b.getBoardContent());
//			pstmt.setString(3, b.getBoardOriginalFileName());
//			pstmt.setString(4, b.getBoardRenamedFileName());
//			pstmt.setInt(5, b.getBoardNo());
//			
//			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
//			//DML은 executeUpdate()
//			result = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
//		
//		return result;
//	}
//
//	public int insertBoardComment(Connection conn, BoardComment bc) {
//		int result = 0;
//		PreparedStatement pstmt = null;
//		String query = prop.getProperty("insertBoardComment"); 
//		//insert into board_comment 
//		//values(seq_board_comment_no.nextval, ?, ?, ?, ?, ?, default)
//		try {
//			//미완성쿼리문을 가지고 객체생성.
//			pstmt = conn.prepareStatement(query);
//			//쿼리문미완성
//			pstmt.setInt(1, bc.getBoardCommentLevel());
//			pstmt.setString(2, bc.getBoardCommentWriter());
//			pstmt.setString(3, bc.getBoardCommentContent());
//			pstmt.setInt(4, bc.getBoardRef());
//			pstmt.setObject(5, bc.getBoardCommentRef() != 0 ? 
//									bc.getBoardCommentRef() : 
//										null);// 댓글인 경우 0번 댓글을 참조
//			
//			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
//			//DML은 executeUpdate()
//			result = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
//		return result;
//	}
//
//	
//	public List<BoardComment> selectCommentList(Connection conn, int board_no) {
//		List<BoardComment> list = new ArrayList<>();
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		
//		String query = prop.getProperty("selectCommentList");
//		
//		try{
//			//미완성쿼리문을 가지고 객체생성. 
//			pstmt = conn.prepareStatement(query);
//			
//			//시작 rownum과 마지막 rownum 구하는 공식
//			pstmt.setInt(1, board_no);
//			
//			//쿼리문실행
//			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
//			rset = pstmt.executeQuery();
//			
//			while(rset.next()){
//				BoardComment bc = new BoardComment();
//				//컬럼명은 대소문자 구분이 없다.
//				bc.setBoardCommentNo(rset.getInt("board_comment_no"));
//				bc.setBoardCommentLevel(rset.getInt("board_comment_level"));
//				bc.setBoardCommentWriter(rset.getString("board_comment_writer"));
//				bc.setBoardCommentContent(rset.getString("board_comment_content"));
//				bc.setBoardRef(rset.getInt("board_ref"));
//				bc.setBoardCommentRef(rset.getInt("board_comment_ref"));//null인 참조댓글필드는 0값이 대입됨.
//				bc.setBoardCommentDate(rset.getDate("board_comment_date"));
//				list.add(bc);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			close(rset);
//			close(pstmt);
//		}
//		
//		
//		return list;
//	}
//

	


	
	
	
}
