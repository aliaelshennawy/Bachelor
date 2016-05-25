class FarmerListsController < ApplicationController
	def index
		@notify_list = FarmerList.all		
	end
end
