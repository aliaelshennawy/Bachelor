class UsersController < ApplicationController

  skip_before_action :verify_authenticity_token
 # before_action :authenticate_user
  respond_to :json
  def index
        @users = User.all
  end
  #d tmam
  def show
    user=User.find(params[:id])
      
    render json: user , status:200
  end
# d tmam
  def create
    user = User.new(user_params)
    if user.save
      render json: {message: "user has been registered succesfully"} , status:200
    else
      render json: {}, status:401
    end
  end

private

  def user_params
    params.require(:user).permit(:name, :password, :password_confirmation,:status)
  end
end