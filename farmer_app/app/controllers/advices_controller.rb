class AdvicesController < ApplicationController
	skip_before_action :verify_authenticity_token
  	respond_to :json

  	def index
  		advices=Advice.all 
  		render json: advices , status:200
  	end
  	def show 
  		advice=Advice.find(params[:id])
  		render json: advice , status:200
  	end
  	def create 
  		advice=Advice.new(advice_params)
  		if advice.save
         @not=Notification.new(:title => "Advice posted", :text =>"لديك نصيحة جديدة")
         @x = Farmerlist.new
         @x.title="لديك نصيحة جديدة"
         @x.icon=0
         @x.save
     reg_ids=User.where(:status => "farmer")
        @not.save!
     for reg_id in reg_ids
     Notification.send_not(reg_id.registeration_id,@not.text,@not.title)
    end
  			render json: {message:"done"},status:200
  		else
  			render json:{result: "NOP",message: "advice cannot be save"},status:422
  		end
  	end
  	private
  	def advice_params
  		params.require(:advice).permit(:photo,:audio,:id)
  	end
end
