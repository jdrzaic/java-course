package hr.fer.zemris.java.hw14.aplikacija5.sqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw14.aplikacija5.dao.DAO;
import hr.fer.zemris.java.hw14.aplikacija5.dao.DAOException;
import hr.fer.zemris.java.hw14.aplikacija5.model.PollEntry;
import hr.fer.zemris.java.hw14.aplikacija5.model.PollOptionsEntry;

/**
 * Razred implementira sucelje {@link DAO}.
 * Implementacija podsustava DAO uporabom tehnologije SQL. Ova
 * konkretna implementacija očekuje da joj veza stoji na raspolaganju
 * preko {@link SQLConnectionProvider} razreda.
 * 
 * @author jelena
 *
 */
public class SQLDAO implements DAO {

	@Override
	public PollEntry getPoll(long id) {
		Connection con = SQLConnectionProvider.getConnection();
		PollEntry entry = null;
		
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("select id, title, message from Polls where id=?");
			pst.setLong(1, Long.valueOf(id));
			try {
				ResultSet rs = pst.executeQuery();
				
				try {
					if (rs != null && rs.next()) {
						entry = new PollEntry(rs.getLong(1), rs.getString(2),
								rs.getString(3));
					}
				} finally {
					try {
						rs.close();
					} catch (Exception ignorable) {}
				}
			} finally {
				try {
					pst.close();
				} catch (Exception ignorable) {}
			}
		} catch (Exception ex) {
			throw new DAOException("Error obtaining poll", ex);
		}
		return entry;
	}

	@Override
	public long addPoll(String title, String message, Connection conection) {
		Connection con = SQLConnectionProvider.getConnection();
		
		//kod inicijalizacije baze
		if(conection != null) {
			con = conection;
		}
		
		PreparedStatement pst = null;
		long newid = -1;
		
		try {
			pst = con.prepareStatement(
					"INSERT INTO Polls (title, message) values (?,?)",
					Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, title);
			pst.setString(2, message);
			try {
				pst.executeUpdate();
				ResultSet rs = pst.getGeneratedKeys();
				try {
					if (rs != null && rs.next()) {
						newid = rs.getLong(1);
					}
				} finally {
					try {
						rs.close();
					} catch (Exception ignorable) {}
				}
			} finally {
				try {
					pst.close();
				} catch (Exception ignorable) {}
			}
		} catch (Exception ex) {
			throw new DAOException("Error obtaining poll", ex);
		}
		return newid;
	}

	@Override
	public long addOption(String title, String link, long pollID, int votes, Connection conection) {

		Connection con = SQLConnectionProvider.getConnection();
		//kod inicijalizacije baze
		if(conection != null) {
			con = conection;
		}
		
		PreparedStatement pst = null;
		long noviID = -1;
		try {
			pst = con
					.prepareStatement(
							"INSERT INTO PollOptions (optionTitle, optionLink, pollID, votesCount) values (?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, title);
			pst.setString(2, link);
			pst.setLong(3, pollID);
			pst.setInt(4, votes);
			try {
				pst.executeUpdate();
				ResultSet rs = pst.getGeneratedKeys();
				try {
					if (rs != null && rs.next()) {
						noviID = rs.getLong(1);
					}
				} finally {
					try {
						rs.close();
					} catch (Exception ignorable) {System.out.print(ignorable.getMessage());}
				}
			} finally {
				try {
					pst.close();
				} catch (Exception ignorable) {
					System.out.print(ignorable.getMessage());
				}
			}
		} catch (Exception ex) {
			throw new DAOException("Pogreška prilikom dohvata korisnika.", ex);
		}
		System.out.print(noviID);
		return noviID;
	}

	@Override
	public List<PollOptionsEntry> getOptions(long id) {
		List<PollOptionsEntry> options = new ArrayList<>();
		Connection con = SQLConnectionProvider.getConnection();
		
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("select id, optionTitle, optionLink, votesCount from PollOptions where pollID=? order by id");
			
			pst.setLong(1, Long.valueOf(id));
			try {
				ResultSet rs = pst.executeQuery();
				try {
					while (rs != null && rs.next()) {
						PollOptionsEntry option = new PollOptionsEntry(
								rs.getLong(1), rs.getString(2),
								rs.getString(3), rs.getInt(4));
		
						options.add(option);
					}
				} finally {
					try {
						rs.close();
					} catch (Exception ignorable) {}
				}
			} finally {
				try {
					pst.close();
				} catch (Exception ignorable) {}
			}
		} catch (Exception ex) {
			throw new DAOException(
					"Error obtaining poll", ex);
		}
		return options;
	}

	@Override
	public Integer getOptionVotesCount(long pollID, long optionID) {
		Integer data = null;
		Connection con = SQLConnectionProvider.getConnection();
		
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("select id, optionTitle, optionLink, votesCount from PollOptions where pollID=? and id=?");
			pst.setLong(1, Long.valueOf(pollID));
			pst.setLong(2, optionID);
			try {
				ResultSet rs = pst.executeQuery();
				try {
					while (rs != null && rs.next()) {
						data = Integer.valueOf(rs.getInt(4));
					}
				} finally {
					try {
						rs.close();
					} catch (Exception ignorable) {}
				}
			} finally {
				try {
					pst.close();
				} catch (Exception ignorable) {}
			}
		} catch (Exception ex) {
			throw new DAOException(
					"Error obtaining poll option", ex);
		}
		return data;
	}

	@Override
	public void updateOptionVotesCount(long pollID, long optionID, int count) {
		
		Connection con = SQLConnectionProvider.getConnection();
		PreparedStatement pst = null;
		
		try {
			pst = con.prepareStatement("update pollOptions set votesCount=? where id=? and pollID=?");
			
			pst.setLong(1, Integer.valueOf(count));
			pst.setLong(2, Long.valueOf(optionID));
			pst.setLong(3, pollID);
			
			try {
				pst.executeUpdate();
			} catch (Exception ex) {
			} finally {
				try {
					pst.close();
				} catch (Exception ignorable) {
				}
			}
		} catch (Exception ex) {
			throw new DAOException(
					"error obtaining poll option", ex);
		}
	}

	@Override
	public boolean PollExists(String title, Connection conection) {
		
		Connection con = SQLConnectionProvider.getConnection();
		
		//kod inicijalizacije baze
		if(conection != null) {
			con = conection;
		}
		
		//ako ne postoji, vracamo false
		boolean exists = false;
		System.out.print("conn" + con.toString());
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("select id, title from Polls where title=?");
			
			pst.setString(1, title);
			try {
				ResultSet rs = pst.executeQuery();
				try {
					while (rs != null && rs.next()) {
						exists = true;
					}
				} finally {
					try {
						rs.close();
					} catch (Exception ignorable) {}
				}
			} finally {
				try {
					pst.close();
				} catch (Exception ignorable) {}
			}
		} catch (Exception ex) {
			throw new DAOException(
					"Error obtaining poll", ex);
		}
		return exists;
	}
	
	@Override
	public List<PollEntry> getPolls() {
		
		List<PollEntry> polls = new ArrayList<>();
		Connection con = SQLConnectionProvider.getConnection();
		
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("select id, title from Polls order by id");
			try {
				ResultSet rs = pst.executeQuery();
				
				try {
					while (rs != null && rs.next()) {
						PollEntry entry = new PollEntry(rs.getLong(1),
								rs.getString(2), "");
						polls.add(entry);
					}
				} finally {
					try {
						rs.close();
					} catch (Exception ignorable) {}
				}
			} finally {
				try {
					pst.close();
				} catch (Exception ignorable) {}
			}
		} catch (Exception ex) {
			throw new DAOException(
					"error obtaining polls", ex);
		}
		return polls;
	}

}
