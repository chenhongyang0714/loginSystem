## 基于Java语言的GUI程序(实现简单的用户登陆系统)

### 利用Mysql数据库对用户信息进行存储(整体基于图书馆操作系统)
> 1. 配置Mysql数据库
> 2. 创建本地数据库
> 3. 创建Tables表格存储信息(包括用户的账号与密码)


## 主要功能

### 登陆
1. 点击Ensure进行判断用户的信息是否正确(用户的密码设置为不可见)
2. 通过弹出小弹窗提示登录是否成功(只有在鼠标点击登录时生效)
3. 点击Exit退出登录程序
4. 在账号文本框按Enter键，光标落在密码文本框
5. 在密码文本框按Enter键，直接执行登陆登录

### 修改密码
1. 


### 退出
1. 弹出Bye的小窗口




<h1>通过连接Mysql数据库进行图书馆的设计</h1>

<h3>本项目的GUI只实现了登陆的功能(设计GUI的类体最好是传值的方式设置信息)</h3>

注意: 
	1. 运行程序前需保证Mysql服务已开启  
	2. 只有在登陆成功数据库之后，才能进行其余操作  
	3. 只能实现同时对一本书进行操作
***

1  
	用于用户登陆图书馆查询系统  
    `public boolean LandingWithPassword();`  
    
	说明:  
	       1. 前提: 数据库users存储有该学生的登陆信息  
	       2. 输入用户的学号作为账号、姓名作为密码-->登陆数据库  
	       3. 如果登陆失败，将自动重新执行登陆操作-->直到登陆成功  
*** 

2  
	查看图书馆中的所有书籍   
	`public void SelectAllBooks();`
	
	说明:  
		1. 返回 BooksStorage 中存储的全部书籍(包括: id  bookName  bookAuthor bookPress  bookNumber)
***   
3     
	根据书名查询 图书  
	`public void SelectDesignatedBook();`
	
	说明：
		1. 如果未查询到对应书名的书籍则-->查询失败
***	
4  
	根据书名 借书  
	`public void BorrowBookByName();`
	
	注意: update 执行语句 要用 prepareStatement，返回值为int型整数
	    select 执行语句 要用 statement
	 
	说明：
	       1. 如果未检测到对应书名的书-->系统检测您输入的书名有误,请重新输入!
	       2. 如果查询的书剩余为零-->非常抱歉,书量不足,借阅失败!
	       3. 总借书周期为30天
***	   
    
5   
	根据书名 还书  
	`public void RepayBookByName();`
	
	说明:
		1. 如果未检测到对应书名的书-->系统检测您输入的书名有误,请重新输入!	
***	
6  
	根据书名 续借  
	`public int RenewBookByName();`
	
	说明:
		1. 如果该书为被借阅,则将无法续借
		2. 如果该书存在,则将可执行延期操作
		3. 将借书时间延至60天
		4. 定义为int型函数: 用于在还没有借书的情况下结束此函数

***
7       
       	根据用户名更改密码
	`public void changePassword(String userAccount, String userPassword);`
	
	









