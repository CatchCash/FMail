#DBUtilsPool:
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
</code>


#RSAUtils:

RSA加密算法。主要用于登陆密码加密，通过此Util调用【generateBase64PublicKey()】获取PublicKey
传至前端JavaScript进行RSA加密,前端确认加密后，才可发送密文。
之后通过传输过来的密文进行私钥解密，与存放在数据库里的密文进行TripleDESUtils解密后比较。
【generateBase64PublicKey()】获取PublicKey-->String PublicKey = RSAUtils.generateBase64PublicKey();
【decryptBase64】进行解密--> String result = RSAUtils.decryptBase64(String src);
假定传输内容含有非ASCII低128位字符。
【decrypt】-->String result = RSAUtils.decrypt(String src);
纯低128位ASCII字符



#TripleDESUtils:

3DES加密算法。主要用于数据库读取和存入密码。
【encrypt】进行加密-->  String result = TripleDESUtiles.encrypt(String src);
【decrypt】进行解密


##PS:
BASE64编码。
比方说RSAUtils中的方法【generateBase64PublicKey()】进行了BASE64加密
因为早期的一些邮件服务器只支持文本信息，不支持二进制信息和文件。
而base64编码后的结果都是ASCII低128位，也就是都是纯文本的，适合用电子邮件来传送二进制数据。
所以可以引申来，在网络上传输含有非ASCII低128位的长串。需要进行加密解密传输。

