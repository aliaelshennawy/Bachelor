class FarmerNotificationsController < ApplicationController
	def index
		@notify_list = Farmer_notifications.all
		
	end
end
