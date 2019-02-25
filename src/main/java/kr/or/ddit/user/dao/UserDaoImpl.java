package kr.or.ddit.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.user.model.UserVo;

public class UserDaoImpl implements IUserDao {

	/**
	 * Method : gertAllUser 작성자 : Hansoo 변경이력 :
	 * 
	 * @return Method 설명 :전체 사용자 조회
	 */
	public List<UserVo> gertAllUser(SqlSession sqlSession) {
		List<UserVo> userList = sqlSession.selectList("user.getAllUser"); // Ibatis에서 queryForList와 동일함
		return userList;
	}

	public UserVo selectUser(SqlSession sqlSession, String userId) {

		UserVo userVo = sqlSession.selectOne("user.selectUser", userId); // Ibatis에서 queryForObject와 비슷함
		return userVo;
	}


	/**
	 * Method : getUserCnt 작성자 : Hansoo 변경이력 :
	 * 
	 * @return Method 설명 : 전체 사용자 수 조회
	 */
	@Override
	public int getUserCnt(SqlSession sqlSession) {
		int userCnt = sqlSession.selectOne("user.getUserCnt");
		return userCnt;
	}

	@Override
	public int insertUser(SqlSession sqlSession, UserVo vo) {

		int insertCnt = sqlSession.insert("user.insertUser", vo);
		sqlSession.commit();

		return insertCnt;
	}

	/**
	 * Method : deleteUser 작성자 : Hansoo 변경이력 :
	 * 
	 * @param userId
	 * @return Method 설명 : 사용자 삭제
	 */
	@Override
	public int deleteUser(SqlSession sqlSession, String userId) {
		int deleteCnt = sqlSession.delete("user.deleteUser", userId);
		sqlSession.commit();
		return deleteCnt;
	}

	@Override
	public int updateUser(SqlSession sqlSession, UserVo vo) {
		int updateCnt = sqlSession.update("user.updateUser", vo);
		return updateCnt;
	}

	@Override
	public int updatePassforEncrypt(SqlSession sqlSession, UserVo vo) {
		 int updateCnt= sqlSession.update("user.updatePassforEncrypt", vo);
		return updateCnt;
	}
}
