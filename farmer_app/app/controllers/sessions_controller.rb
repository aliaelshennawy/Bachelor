class SessionsController < ApplicationController
  skip_before_action :verify_authenticity_token
def new
  end
 def create
    user = User.find_by_name(params[:session][:name])
    # If the user exists AND the password entered is correct.
    if user && user.authenticate(params[:session][:password])
      # Save the user id inside the browser cookie. This is how we keep the user 
      # logged in when they navigate around our website.
      session[:user_id] = user.id
      render json: user, status: 200
    else
      render json: {}, status: 401
    end
  end

  def destroy
    session[:user_id] = nil
    redirect_to '/login'
  end

end