package PA4;

interface Tweet {
	// 2.1: latestTweetOnThread
	Tweet latestTweetOnThread();
	
	// 2.2: longestUsernameOnThread
	User longestUsernameOnThread();
	
	// Provided methods, DO NOT MODIFY
    User getAuthor();
    String getContents();
    int getLikes();
    Tweet getReplyTo();
    String getDate();
}

//User class, DO NOT MODIFY
class User {
	String username, displayName;
	int followers;
	User(String username, String displayName, int followers) {
		this.username = username;
		this.displayName = displayName;
		this.followers = followers;
	}
	
	String toText() {
		return username + " @" + displayName;
	}
}

class TextTweet implements Tweet {
	User author;
	String contents;
	String date;
	int likes;
	
	TextTweet(String contents, User author, int likes, String date) {
		this.author = author;
		this.contents = contents;
		this.likes = likes;
		this.date = date;
	}

	// 2.1
	@Override
	public Tweet latestTweetOnThread() {
		return this;
	}

	// 2.2
	@Override
	public User longestUsernameOnThread() {
		return author;
	}

	@Override
	public User getAuthor() {
		return author;
	}

	@Override
	public String getContents() {
		return contents;
	}

	@Override
	public int getLikes() {
		return likes;
	}

	@Override
	public Tweet getReplyTo() {
		return null;
	}

	@Override
	public String getDate() {
		return date;
	}
}

class ReplyTweet implements Tweet {
	User author;
    String contents;
    int likes;
    String date; 
    Tweet replyTo;
    
    ReplyTweet(String contents, User author, int likes, String date, Tweet replyTo) {
    	this.author = author;
        this.contents = contents;
        this.likes = likes;
        this.date = date;
        this.replyTo = replyTo;
    }

    // 2.1
	@Override
	public Tweet latestTweetOnThread() {
		int replyMonth = Integer.valueOf(replyTo.getDate().substring(0, 1));
		int replyYear = Integer.valueOf(replyTo.getDate().substring(3, 6));;
		
		int thisMonth = Integer.valueOf(this.date.substring(0, 1));
		int thisYear = Integer.valueOf(this.date.substring(3, 6));
		
		if(this.getReplyTo() != null) {
			
		}
		
		// if the years are not the same
		if(thisYear != replyYear) {
			if(thisYear > replyYear) {
				return this;
			} else {
				return replyTo;
			}
		} else {
			// The years are the same; compare the months
			if(thisMonth > replyMonth) {
				return this;
			} else {
				return replyTo;
			}
		}
	}

	// 2.2
	@Override
	public User longestUsernameOnThread() {
		if(this.author.username.length() > replyTo.getAuthor().username.length()) {
			return this.author;
		}
		else {
			return replyTo.getAuthor();
		}
	}

	@Override
	public User getAuthor() {
		return author;
	}

	@Override
	public String getContents() {
		return contents;
	}

	@Override
	public int getLikes() {
		return likes;
	}

	@Override
	public Tweet getReplyTo() {
		return replyTo;
	}

	@Override
	public String getDate() {
		return date;
	}
	
}

class TweetTester {
	public static void main(String[] args) {
		 User u1 = new User("greg", "Greg", 12);
		 User u2 = new User("greg2", "Greg2", 12);
		 User u3 = new User("greg23", "Greg2", 12);
		 
		 Tweet t1 = new TextTweet("We're already halfway through with the quarter", u3, 12, "05-2022");
		 Tweet t2 = new ReplyTweet("Yeah, can you believe it. It still feel like the beginning of the quarter", u2, 13, "04-2021", t1);
		 Tweet t3 = new ReplyTweet("This is the third tweet", u1, 15, "07-2021", t2);
		 
		 System.out.println(t3.latestTweetOnThread().getContents());
		 System.out.println(t3.longestUsernameOnThread().username);
	}
}
