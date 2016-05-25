class FarmerTefsController < ApplicationController
	def index
		@notify_list = Farmer_tef.all		
	end
end
