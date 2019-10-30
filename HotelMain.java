Scanner scanner = new Scanner(System.in);

		//System.out.println("Please enter your full name:");
		//String name = scanner.next();
		System.out.println("Please enter the room you would like to book");
		int roomnum = scanner.nextInt();
		//System.out.println("Please enter the number of nights you would like to stay:");
		//int numNights = scanner.nextInt();
		//System.out.println("Please enter the day you would like the room (mm/dd/yyyy)");
		//String date = scanner.next();
		room.put(roomnum, true);
		// scanner.close();
		System.out.println(room);

		connect=DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/sys","root","12345678");
		statement = connect.createStatement();
		//rs = statement.executeQuery("select roomNum from test_table");
		java.sql.PreparedStatement ps = connect.prepareStatement("select count(*) from test_table where roomNum = ?");
		ps.setInt(1,roomnum);
		ResultSet rs = ps.executeQuery();
		int n = 0;
		int s = 0;
		if(rs.next()) {
		n = rs.getInt(1);
		//s = rs.getInt(2);
		}
		if ( n == 0 ) { //The room they want to book is available, so store room in database.
		   // do what ever you need, if the row exists
			 String query = " insert into test_table (roomNum, status)"
				        + " values (?, ?)";
			 java.sql.PreparedStatement preparedStmt = connect.prepareStatement(query);
		     preparedStmt.setInt (1, roomnum);
		     preparedStmt.setInt (2, 0); //0=clean 1=dirty

		     preparedStmt.execute();
		}

		else { //Room is alredy taken, notify guest and restart so they can try a different room
			System.out.println(n);
			System.out.println("The room you are trying to get is booked already");
			guestBookRoom();
		}
