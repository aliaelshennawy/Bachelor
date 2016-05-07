sclass Picture < ActiveRecord::Base
  
  def user_params
      params.require(:Picture).permit(:name, :image)
    end
  has_attached_file :image,
                    :styles => { :original => "480x480>", :thumbnail => "100x100>" },
                    :path => ':rails_root/public/system/pictures/image/:id_partition/:style_:filename'
end
