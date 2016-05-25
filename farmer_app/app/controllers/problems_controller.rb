class ProblemsController < ApplicationController
  skip_before_action :verify_authenticity_token
  # def index
  #   @photos = Problem.order('created_at')
  # end

  # def new
  #   @photo = Problem.new
  # end

  # def create
  #   @photo = Problem.new(params[:image])
  #   if @photo.save
  #     flash[:success] = "The photo was added!"
  #     #redirect_to root_path
  #     render :json => {message: "photo has been saved succesfully"}, status: 200
      
  #   else
  #     render :json => {result: "NOK" , message: "photo cannot be save"}, status: 422
  #   end
  # end
  def create
    @problem = Problem.new(problem_params)
    #@problem.user_id = params[:user_id]
    if @problem.save
      @not=Notification.new(:cause_id => @problem.user_id,:title => "Problem posted", :text =>"لديك سؤال جديد")
     reg_ids=User.where(:status => "engineer")
        @not.save!
        @y=Engineerlist.new
         @y.title="لديك سؤال جديد"
         @y.icon=2
         @y.save
     for reg_id in reg_ids
     Notification.send_not(reg_id.registeration_id,@not.text,@not.title)
    end
      render :json => {message: "problem has been saved succesfully"}, status: 200
    else
     render :json => {result: "NOK" , message: "photo cannot be save"}, status: 422
    end
  end
#   def getProblems
#   @problems= Problem.all
 
#   render json: @problems
# end
# d tamam (Y)
 def index
    @problems=Problem.all
    render json:@problems , status:200
  end

#d tamam
  def index_audio
    @problems=Problem.all
    @audio=@problems.map{|e| {audio:e.audio , id:e.id}}
    render json:@audio , status:200
  end
  #d tmam
   def index_photo
    @problems=Problem.all
    @photo=@problems.map{|e| {photo:e.photo , id:e.id}}
    render json:@photo , status:200
  end

  private
  def problem_params
    params.require(:problem).permit(:photo, :title , :audio , :user_id )
  end
end