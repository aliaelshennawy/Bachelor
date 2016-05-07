class PictureController < ApplicationController

skip_before_action :verify_authenticity_token

# POST /pictures                                                                                                                                                              
# POST /pictures.json
def create
	  @picture = Picture.new(params[:picture])

	  respond_to do |format|
	    if @picture.save
	      format.html { redirect_to @picture, notice: 'Picture was successfully created.' }
	      format.json { render json: @picture, status: :created, location: @picture }
	    else
	      format.html { render action: "new" }
	      format.json { render json: @picture.errors, status: :unprocessable_entity }
	    end
	  end
	end
end
