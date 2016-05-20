class AddRegisterationIdToUser < ActiveRecord::Migration
  def change
    add_column :users, :registeration_id, :string
  end
end
