class FarmerlistsController < ApplicationController
	def index
		@notify_list = Farmerlist.all
		render json:@notify_list		
	end
end
