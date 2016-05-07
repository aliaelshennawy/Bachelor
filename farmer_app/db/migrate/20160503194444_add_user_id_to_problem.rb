class AddUserIdToProblem < ActiveRecord::Migration
  def change
    add_column :problems, :id, :integer
  end
end
