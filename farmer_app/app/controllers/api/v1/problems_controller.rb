class Api::V1::ProblemsController < ApplicationController
	skip_before_action :verify_authenticity_token
	respond_to :json
	 def create
    @photo = Problem.new(problem_params)
    #if @photo.save
      #flash[:success] = "The photo was added!"
      #redirect_to root_path
      render :json => {message: "photo has been saved succesfully"}, status: 200
      
    #else
     # render :json => {result: "NOK" , message: "photo cannot be save"}, status: 422
    #end
  end
  def index
  	@problems=Problem.all
  	render json:@problems , status:200
  end
  private

  def problem_params
    params.require(:problem).permit(:photo, :title)
  end
end
