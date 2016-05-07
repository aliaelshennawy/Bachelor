class User < ActiveRecord::Base
	  has_secure_password
	  has_and_belongs_to_many(:userProblems,
	  	:join_table => "user_problems",
	  	:foreign_key => "user_a_id",
	  	:assocition_foreign_key => "user_b_id",
	  	:class_name => "User"
	  	)
	  has_many :problems
	

end
