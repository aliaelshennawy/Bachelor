class AddPhotoToAdvice < ActiveRecord::Migration
  def change
    add_column :advices, :photo, :string
  end
end
