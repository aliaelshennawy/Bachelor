class RepliesController < ApplicationController
	skip_before_action :verify_authenticity_token
  	respond_to :json
  	def create
  		reply=Reply.new(reply_params)
  		if reply.save
        	@not=Notification.new(:user_id => Problem.find(reply.problem_id).user_id,:title => "Reply posted", :text =>"لقد جاوب المهندس على سؤالك")
     		@not.save!
     	 @y=Farmerlist.new
         @y.title="لديك اجابة جديدة"
         @y.icon=1
         @y.save
        	reg_ids=User.find(@not.user_id).registeration_id
     		#for reg_id in reg_ids
     			Notification.send_not(reg_ids,@not.text,@not.title)
    		#end
  			render json: {message: "reply has been saved "}, status:200  		
  		else
  			render json: {message: "problem !"} , status:422
  		end
  	end
  	def index
  		replies=Reply.where(:problem_id => params[:problem_id])
  		render json: replies , status: 200
  	end

  	def show 
  		reply=Reply.find(params[:id])
  		render json: reply , status:200
  	end
  	private

  	def reply_params
  		params.require(:reply).permit(:photo,:audio,:problem_id,:id)
  	end
  
end
