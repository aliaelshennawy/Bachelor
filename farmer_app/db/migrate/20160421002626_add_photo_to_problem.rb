class AddPhotoToProblem < ActiveRecord::Migration
  def change
    add_column :problems, :photo, :string
  end
end
