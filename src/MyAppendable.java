import java.io.IOException;

/**
 * An object that implements the Appendable interface and throws an IOException for each method.
 * This is testing purposes only.
 */
public class MyAppendable implements Appendable {
  /**
   * Appends the specified character sequence to this {@code Appendable}.
   *
   * <p>Depending on which class implements the character sequence
   * {@code csq}, the entire sequence may not be appended.
   *
   * @param csq The character sequence to append.  If {@code csq} is {@code null}, then the four
   *            characters {@code "null"} are appended to this Appendable.
   * @return A reference to this {@code Appendable}
   * @throws IOException If an I/O error occurs
   */
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("For testing purposes.");
  }

  /**
   * Appends a subsequence of the specified character sequence to this {@code Appendable}.
   *
   * <p>An invocation of this method of the form {@code out.append(csq, start, end)}
   * when {@code csq} is not {@code null}, behaves in exactly the same way as the invocation
   *
   * <pre>
   *     out.append(csq.subSequence(start, end)) </pre>
   *
   * @param csq   The character sequence from which a subsequence will be appended.  If {@code csq}
   *              is {@code null}, then characters will be appended as if {@code csq} contained the
   *              four characters {@code "null"}.
   * @param start The index of the first character in the subsequence
   * @param end   The index of the character following the last character in the subsequence
   * @return A reference to this {@code Appendable}
   * @throws IndexOutOfBoundsException If {@code start} or {@code end} are negative, {@code start}
   *                                   is greater than {@code end}, or {@code end} is greater than
   *                                   {@code csq.length()}
   * @throws IOException               If an I/O error occurs
   */
  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("For testing purposes.");
  }

  /**
   * Appends the specified character to this {@code Appendable}.
   *
   * @param c The character to append
   * @return A reference to this {@code Appendable}
   * @throws IOException If an I/O error occurs
   */
  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("For testing purposes.");
  }
}
