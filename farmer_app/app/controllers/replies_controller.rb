class RepliesController < ApplicationController
	skip_before_action :verify_authenticity_token
  	respond_to :json
  	def create
  		reply=Reply.new(reply_params)
  		if reply.save
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
  		param.require(:reply).permit(:photo,:audio,:audio_id,:id)
  	end
  
end
