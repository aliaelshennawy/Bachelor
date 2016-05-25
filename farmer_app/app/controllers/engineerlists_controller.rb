class EngineerlistsController < ApplicationController
def index
		@notify_list = Engineerlist.all
		render json:@notify_list		
	end
end
