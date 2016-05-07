class AddUserIdToAdvice < ActiveRecord::Migration
  def change
    add_column :advices, :id, :integer
  end
end
