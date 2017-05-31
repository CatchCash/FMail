DBUtilsPool:
数据库连接池
通过对象调用，getInstance()获取连接对象
调用完后使用release(connection)释放连接
此2个步骤为必须。
模板：
	public User getUser(String uid) {
		User result=null;
	    DBUtilsPool pool = null;
	    Connection con = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.getConnection();
		} catch (Exception se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		try {
		    /*select 之后的字段必须指定，不准使用*替代*/
			String sql = "select uid,nickName from userInfo where uid = ?"
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, uid);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result= new UserInfo(resultSet.getString(1), resultSet.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.release(con);
		}
		return result;
	}



RSAUtils:
RSA加密算法。主要用于登陆密码加密，通过此Util调用generateBase64PublicKey()获取PublicKey
传至前端JavaScript进行RSA加密,前端确认加密后，才可发送密文。
之后通过传输过来的密文进行私钥解密，与存放在数据库里的密文进行3DESUtils解密后比较。
