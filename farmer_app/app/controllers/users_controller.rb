class UsersController < ApplicationController

  skip_before_action :verify_authenticity_token
  respond_to :jason
  def index
        @users = User.all
  end
  
  def show
    user=User.find(params[:name])
    render json: user
  end

  def create
    user = User.new(user_params)
    if user.save
      session[:user_id] = user.id
      #redirect_to '/'
      render json: user
    else
      #redirect_to '/signup'
      render json: {}, status:401
    end
  end

private

  def user_params
    params.require(:user).permit(:name, :password, :password_confirmation)
  end
end