class Problem < ActiveRecord::Base
has_attached_file :image
has_attached_file :image,
                  styles: { thumb: ["64x64#", :jpg] }
belongs_to :user      
validates_attachment :image,
                     content_type: { content_type: ["image/jpeg", "image/gif", "image/png"] }
end
