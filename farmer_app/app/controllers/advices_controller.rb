class AdvicesController < ApplicationController
	skip_before_action :verify_authenticity_token
  	respond_to :json

  	def index
  		advices=Advice.all 
  		render json: advies , status:200
  	end
  	def show 
  		advice=Advice.find(params[:id])
  		render json: advice , status:200
  	end
  	def create 
  		advice=Advice.new(advice_params)
  		if advice.save
  			render json: {message:"done"},status:200
  		else
  			render json:{"NOP"},status:422
  		end
  	end
  	private
  	def advice_params
  		params.require(:advice).permit(:photo,:audio,:id)
  	end
end
