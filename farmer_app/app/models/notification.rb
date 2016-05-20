class Notification < ActiveRecord::Base

	belongs_to:user
	def self.send_not(register_id,content,title)
		gcm=GCM.new("AIzaSyC-X6g0T00mAfZToz2DTomvbah1rnEX4vA")
		registeration_ids=[register_id]
		response=gcm.send(registeration_ids,{data: {message:content , title:title }})
	end
end
