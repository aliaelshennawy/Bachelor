class RemoveEmailFromTableUsers < ActiveRecord::Migration
  def change
    remove_column :users, :email, :string
  end
end
