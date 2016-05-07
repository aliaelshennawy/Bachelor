class AddUserIdToUser < ActiveRecord::Migration
  def change
    add_column :users, :id, :integer
  end
end
